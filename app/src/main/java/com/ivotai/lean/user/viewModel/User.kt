package com.ivotai.lean.user.viewModel

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class User(
        @Id(assignable = true)
        var id: Long, //1
        val avatar: String, //dd
        val name: String //li
)