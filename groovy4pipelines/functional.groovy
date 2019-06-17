#!groovy
println("Those are the numbers!")
list = [1, 2, 3, 4, 5, 6, 7, 8, 9]
println((1..9))
assert list = (1..9)

def evens = list.findAll { Integer i -> ! i % 2 }
def odds = list.findAll { it % 2 }
println("Odd numbers:")
println(odds)
println("Even numbers:")
println(evens)
assert odds == [1, 3, 5, 7, 9]
assert evens.intersect(odds) == []

println('Alphabet')
println(('a'..'z'))
assert (1..26).collect{
    it + 96 as char
} == ('a'..'z')

println('Iterate over each odd')
odds.eachWithIndex { odd, index ->
    println "${index}: ${odd}"
}
println('I do not care about odds, just count me the index')
odds.eachWithIndex { _, index ->
    println "I can't ignore that ${_}"
}

println "Collect entries is very often used to create map of closures to execute in parallel"
println """In this example we say: for each number in 1,2,3
create me a map where key is a number, and value is a function which takes
argument called sum and adds it to number"""
map = [1, 2, 3].collectEntries{ return [
  (it): { sum -> it + sum }
]}
map.each { key, value ->
  assert key * 2 == value(key)
}
println map

println "Inject operation allows to pass variable into each iteration on list"
println "This is my favourite groovy construction as it implements builder pattern xD"
('b'..'z').inject("alphabet: a") {alphabet, letter ->
  alphabet += ", ${letter}"
}

println "Print all files with yml or yaml extension in the current directory"
println "WARNING! This will not work in pipelines as we are not using direct shell calls, but this works in remote groovy shell for agents connected via ssh or jnlp, pipelines version is sh step"

"ls -al".execute().text.split('\n').findAll {
  it.contains('groovy')
}.collect {
  it.split().last()
}