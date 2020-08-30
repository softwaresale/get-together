package com.softwaresale.gettogether.group

import com.softwaresale.gettogether.user.User
import com.softwaresale.gettogether.user.UserService
import org.springframework.stereotype.Service

@Service
class GroupService(
        private val groupRepository: GroupRepository,
        private val userService: UserService
) {
    fun getAll(): Iterable<Group> = groupRepository.findAll()

    fun getById(id: Long): Group? {
        return with(groupRepository.findById(id)) {
            if (this.isPresent) this.get() else null
        }
    }

    fun save(group: Group) = groupRepository.save(group)

    /**
     * Creates a new group with the current user as the leader
     * @param group The group to save
     * @return The created group, or null if the user is not logged in
     */
    fun create(group: Group): Group? {
        return this.userService.getCurrentUser()?.let {
            group.modifyLeader(it)
            return this.save(group)
        }
    }

    fun addUserToGroup(group: Group, user: User): Group {
        group.addMember(user)
        return groupRepository.save(group)
    }
}