package com.softwaresale.gettogether.group

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.softwaresale.gettogether.user.User
import javax.persistence.*

@Entity(name = "GetTogetherGroup")
class Group(
       val name: String,

       @ManyToMany(mappedBy = "memberGroups", cascade = [CascadeType.ALL])
       @JsonManagedReference
       val members: MutableList<User> = mutableListOf(),

       @Id @GeneratedValue
       val id: Long?,
       val description: String?,

       @ManyToOne
       @JsonManagedReference
       var leader: User?
) {
       fun modifyLeader(user: User) {
              this.leader = user
              user.ownedGroups.add(this)
       }

       fun removeLeader(user: User) {
              user.ownedGroups.remove(this)
       }

       fun addMember(user: User) {
              members.add(user)
              user.memberGroups.add(this)
       }

       fun removeMember(user: User) {
              members.remove(user)
              user.memberGroups.remove(this)
       }
}