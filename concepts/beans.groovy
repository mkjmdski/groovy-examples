#!groovy
class AGroovyBean {
  String color
}

def myGroovyBean = new AGroovyBean()

myGroovyBean.setColor('baby blue')
assert myGroovyBean.getColor() == 'baby blue'

myGroovyBean.color = 'pewter'
assert myGroovyBean.color == 'pewter'

// In groovy you can use named constructor to pass a map which will populate object properties
def newBean = new AGroovyBean(
  color: 'red'
)
println newBean.color