class Bucket {
    int size

    Bucket(int size) { this.size = size }

    Bucket plus(Bucket other) {
        return new Bucket(this.size + other.size)
    }
}
// source http://groovy-lang.org/operators.html#Operator-Overloading

def b1 = new Bucket(4)
def b2 = new Bucket(11)
assert (b1 + b2).size == 15

// It is useful in pipelines when for example adding the Path objects after running search on some glob, then you can summarazie and substract them like you'd do with string