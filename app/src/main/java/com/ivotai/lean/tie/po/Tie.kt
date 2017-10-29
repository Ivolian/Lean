package com.ivotai.lean.tie.po

import com.ivotai.lean.user.po.User
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.util.*

@Entity
class Tie {
    @Id(assignable = true)
    var id: Long = 0
    var thumbCount: Int = 0
    lateinit var content: String
    lateinit var createTime: Date
    lateinit var pic: String
    lateinit var poster: ToOne<User>
}
