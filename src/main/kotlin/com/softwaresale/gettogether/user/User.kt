package com.softwaresale.gettogether.user

import com.softwaresale.gettogether.group.Group
import javax.persistence.*

@Entity
class User(
        // Properties defined by the Auth0 User model
        val username: String,
        val picture: String,
        val nickname: String,
        val name: String,
        @Id val user_id: String,

        @OneToMany(mappedBy = "leader", cascade = [CascadeType.ALL])
        val ownedGroups: MutableList<Group> = mutableListOf(),
        @ManyToMany(mappedBy = "members", cascade = [CascadeType.ALL])
        val memberGroups: MutableList<Group> = mutableListOf()
)