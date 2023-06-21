package dev.anmatolay.artistarchivist.util.extension


fun <T> Iterable<T>.takeIf(n: Int, predicate: Boolean): List<T> =
    if (predicate) this.take(n) else this.toList()