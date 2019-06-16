class Bucket {
    int size

    Bucket(int size) { this.size = size }

    Bucket plus(Bucket other) {
        return new Bucket(this.size + other.size)
    }
}
// Just by implementing the plus() method, the Bucket class can now be used with the + operator like so:
// def b1 = new Bucket(4)
// def b2 = new Bucket(11)
// assert (b1 + b2).size == 15
// source http://groovy-lang.org/operators.html#Operator-Overloading