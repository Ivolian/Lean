package com.ivotai.lean.tie.dto

import com.ivotai.lean.tie.Tie
import com.ivotai.lean.user.viewModel.User
import java.util.*


data class TieWrapper(
        val id: Long,
        val content: String,
        val createTime: Long,
        val thumbCount: Int,
        val pic: String,
        val poster: User
) {

    companion object {

        fun fromTie(tie: Tie): TieWrapper {
            with(tie) {
                return TieWrapper(
                        id = id,
                        content = content,
                        createTime = createTime.time,
                        thumbCount = thumbCount,
                        pic = pic,
                        poster = poster.target
                )
            }
        }

        fun toTie(tieWrapper: TieWrapper): Tie {
            with(tieWrapper) {
                val tie = Tie()
                tie.id = id
                tie.content = content
                tie.createTime = Date(createTime)
                tie.thumbCount = thumbCount
                tie.pic = pic
                tie.poster.target = poster
                return tie
            }
        }

    }

}