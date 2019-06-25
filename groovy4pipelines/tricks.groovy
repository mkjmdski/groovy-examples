numbers = [1, 3, 5, 9, 8, 0, 9]
new_numbers = [1, 2 ,5, 8, 9, 4, 11]
println new_numbers.findAll{it % 3 == 0} ?: new_numbers.findAll{ it % 2 == 0}