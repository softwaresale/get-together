package com.softwaresale.gettogether.user

import com.softwaresale.gettogether.auth0.Auth0UserInfo
import com.softwaresale.gettogether.group.Group
import javax.persistence.*

@Entity(name = "GetTogetherUser")
class User(
        // Properties defined by the Auth0 User model
        val picture: String,
        val nickname: String,
        val name: String,
        @Id val id: String,

        @OneToMany(mappedBy = "leader", cascade = [CascadeType.ALL])
        val ownedGroups: MutableList<Group> = mutableListOf(),
        @ManyToMany(cascade = [CascadeType.ALL])
        val memberGroups: MutableList<Group> = mutableListOf()
) {
        companion object {
                @JvmStatic
                fun fromAuth0UserInfo(info: Auth0UserInfo): User {
                        return User(
                                picture = info.picture,
                                nickname = info.nickname,
                                name = info.name,
                                id = info.sub
                        )
                }
        }
}