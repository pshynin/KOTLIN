class Player(_name: String) {
    var name: String = _name
        get() = field.capitalize()
        set(value) {
            field = value.reversed()
        }

/*    fun getName(): String {
        return this.name
    }*/
}