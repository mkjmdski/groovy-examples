// In computer programming, a trait is a concept used in object-oriented programming, which represents a set of methods that can be used to extend the functionality of a class.
trait FlyingAbility { /* declaration of a trait */
  String fly() { "I'm flying!" } /* declaration of a method inside a trait */
}

class Bird implements FlyingAbility {} /* Adds the trait FlyingAbility to the Bird class capabilities */
def bird = new Bird() /* instantiate a new Bird */
assert bird.fly() == "I'm flying!" /* the Bird class automatically gets the behavior of the FlyingAbility trait */