def loadScript(fileName) {
    return new GroovyShell().parse( 
        new File(fileName) 
    ).run()
}

def ls(dir=".") {
    dh = new File(dir)
    dh.eachFile {
        println(it)
    }
}

