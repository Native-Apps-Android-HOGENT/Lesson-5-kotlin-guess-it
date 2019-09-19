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
