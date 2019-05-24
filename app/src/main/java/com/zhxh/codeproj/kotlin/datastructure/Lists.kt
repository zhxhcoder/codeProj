package cnife.datastructure

interface List<out E> : Collection<E> {
    operator fun get(index: Int): E

    fun indexOf(element: @UnsafeVariance E): Int

    fun lastIndexOf(element: @UnsafeVariance E): Int

    fun listIterator(index: Int = 0): ListIterator<E>

    fun subList(range: IntRange): List<E>
}

interface MutableList<E> : List<E>, MutableCollection<E> {
    fun add(index: Int, element: E)

    fun addAll(index: Int, elements: Collection<E>)

    override fun add(element: E): Boolean {
        add(size, element)
        return true
    }

    override fun addAll(elements: Collection<E>): Boolean {
        addAll(size, elements)
        return true
    }

    fun set(index: Int, element: E): E

    fun removeAt(index: Int): E
}