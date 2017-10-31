package com.ivotai.lean.user.po

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
  
@Entity
data class User(
        @Id(assignable = true)
        var id: Long,
        val avatar: String,
        val name: String
)