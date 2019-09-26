---
author: Jens Buysse, Harm De Weirdt
title: Android Architecture
date: September, 2019
---

# Lesson 1

## Guess the word

## Exercise 1

Find the solution. What happens to the execution of the program with the following
code snippet

##
```kotlin
init {
    resetList()
    nextWord()
}

// The current word
var word = ""

// The current score
var score = 0

// The list of words - the front of the list is the next word to guess
private lateinit var wordList: MutableList<String>

```

# ViewModel and MVVM

## ViewModel
![](assets/viewmodel.png)

- During configuration changes fragments are re-created. However, ViewModel instances survive. 

## MVVM 
![](assets/viewmodelarch.png)


# Use a ViewModelFactory

## ViewModelFactory

- You want a ViewModel to hold the score to be displayed by the ScoreFragment. 
- You will have to pass in the score value during the ViewModel initialization using the factory method pattern.

##

```kotlin 
class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```
##

The `isAssignableFrom()` determines if the class or interface represented
by this Class object is either the same as, or is a superclass or superinterface of,
the class or interface represented by the specified Class parameter

##
````kotlin
viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
viewModel = ViewModelProviders.of(this, viewModelFactory)
.get(ScoreViewModel::class.java)
````

# Remember passing arguments

## Use Safe Args to pass data with type safety

- A class is created for each destination where an action originates. The name: name of the originating destination, appended with the word "Directions".
- For each action used to pass the argument, an inner class is created whose name is based on the action.
- A class is created for the receiving destination. The name of this class is the name of the destination, appended with the word "Args".

##
````kotlin
 // Sets up event listening to navigate the player when the game is finished
        viewModel.eventGameFinish.observe(this, Observer { isFinished ->
            if (isFinished) {
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController(this).navigate(action)
                viewModel.onGameFinishComplete()
            }
        })
````
## 

````kotlin
val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
````

# Backing property

## 

````kotlin 
// The current word
private val _word = MutableLiveData<String>()
val word: LiveData<String>
    get() = _word
````

##

If a class has two properties which are conceptually the same but one is part of a public API
and another is an implementation detail, use an underscore as the prefix for
the name of the private property.

## Field vs Property

## 

This is an example of a Java field:
```java
public class Person{
    public String name = "Jules";
}
```
Here is an example of a Kotlin property:`
```kotlin
class Person{
    var name: String = "Jules"
}

```

## 
Direct Java equivalent of above Kotlin property is following:

```java
public class Person{
    private String name = "Jules";
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```

##

The default implementation of Kotlin property includes field and accessors:

- getter for val
- getter and setter for var 

Thanks to that, we can always replace accessors default implementation with a custom one.

# Companion Objects

# Object code kotlin


