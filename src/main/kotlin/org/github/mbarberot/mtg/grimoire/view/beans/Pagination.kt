package org.github.mbarberot.mtg.grimoire.view.beans

data class Pagination (
        val first : Link,
        val last : Link,
        val pages : List<Link>
)