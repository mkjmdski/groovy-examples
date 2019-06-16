println "Collect entries is very often used to create map of closures to execute in parallel"
[1, 2, 3].collectEntries{ return [
  (it): { sum ->
    println it + sum
  }
]}.each {
  it.value(1)
}

println "Inject operation allows to pass variable into each iteration on list"
['b', 'c', 'd'].inject("alphabet: a") {alphabet, letter ->
  alphabet += ", ${letter}"
} + "..."

println "Print all files with yml or yaml extension in the current directory"
"ls -al".execute().text.split('\n').findAll {
  it.contains('yml') || it.contains('yaml')
}.collect {
  it.split().last()
}


def joinTwoWordsWithSymbol = { symbol, first, second ->
  first + symbol + second
}
assert joinTwoWordsWithSymbol('#', 'Hello', 'World') == 'Hello#World'

// set symbol to ' ' and create new closure that way
def concatWords = joinTwoWordsWithSymbol.curry(' ')
assert concatWords('Hello', 'World') == 'Hello World'

def prependHello = concatWords.curry('Hello')
//def prependHello = joinTwoWordsWithSymbol.curry(' ', 'Hello')
assert prependHello('World') == 'Hello World'

def power = { BigDecimal value, BigDecimal power ->
  value**power
}

def square = power.rcurry(2)
def cube = power.rcurry(3)

assert power(2, 2) == 4
assert square(4) == 16
assert cube(3) == 27
