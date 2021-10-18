package com.rahul.acro.model

data class AcroResponse(
    val sf: String,
    val lfs: List<Lfs>
)

data class Lfs(
    val lf: String,
    val freq: Int,
    val since: Int,
    val vars: List<Vars>
)

data class Vars(
    val lf: String,
    val freq: Int,
    val since: Int
)
