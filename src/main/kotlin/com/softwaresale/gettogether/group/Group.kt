package com.softwaresale.gettogether.group

import com.softwaresale.gettogether.user.User
import javax.persistence.*

@Entity(name = "GetTogetherGroup")
class Group(
       val name: String,
       @ManyToOne
       val leader: User,
       @ManyToMany(mappedBy = "memberGroups", cascade = [CascadeType.ALL])
       val members: MutableList<User> = mutableListOf(),
       @Id @GeneratedValue
       val id: Long?,
       val description: String?
)