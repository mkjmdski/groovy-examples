// $ python3
// >>> def foo(x):
// ...     print(x + 1)
// ...
// >>> a = foo
// >>> foo(2)
// 3

void joinTwoWordsWithSymbol(symbol, first, second) {
  first + symbol + second
}

def closureWrapper = joinTwoWordsWithSymbol

assert joinTwoWordsWithSymbol('#', 'Hello', 'World') == 'Hello#World'
assert joinTwoWordsWithSymbol('#', 'Hello', 'World') == closureWrapper('#', 'Hello', 'World')

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
