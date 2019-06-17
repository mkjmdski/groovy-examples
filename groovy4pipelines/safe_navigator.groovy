// In object-oriented programming, the safe navigation operator (also known as optional chaining operator, safe call operator, null-conditional operator) is a binary operator that returns null if its first argument is null; otherwise it performs a dereferencing operation as specified by the second argument (typically an object member access or an array index).
// It is used to avoid sequential explicit null checks and assignments and replace them with method/property chaining. In programming languages where the navigation operator (e.g. ".") leads to an error if applied to a null object, the safe navigation operator stops the evaluation of a method/field chain and returns null as the value of the chain expression

def articles = [
    [
        author: [
            name: "Zbigniew",
            surname: "Wodecki"
        ]
    ]
]

println articles[0]?.author?.name
println articles[1]?.author?.name ?: "no such article"
println articles[1] ? "there is article" : "no such article"
println articles[0] ?: "no such article"

def params = [
    foo: 'bar',
]

println params.foo ?: 'this will not be displayed'
println params.baz ?: 'this is displayed'
println params.baz ?: params.foo != 'fooz' ? 'only if not baz and foo is not equal fooz' : 'fooz'

// usage of this operator is great when you want to avoid complex ifology in the code