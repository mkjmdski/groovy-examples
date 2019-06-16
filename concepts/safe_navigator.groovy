def articles = [
    [
        author: [
            name: "Zbigniew",
            surname: "Wodecki"
        ]
    ]
]
println articles[0]?.author?.name
println articles[1]?.author?.name
println articles[1] ? "there is article" : "no such article"
println articles[0] ?: "no such article"
// In object-oriented programming, the safe navigation operator (also known as optional chaining operator, safe call operator, null-conditional operator) is a binary operator that returns null if its first argument is null; otherwise it performs a dereferencing operation as specified by the second argument (typically an object member access or an array index).

// It is used to avoid sequential explicit null checks and assignments and replace them with method/property chaining. In programming languages where the navigation operator (e.g. ".") leads to an error if applied to a null object, the safe navigation operator stops the evaluation of a method/field chain and returns null as the value of the chain expression