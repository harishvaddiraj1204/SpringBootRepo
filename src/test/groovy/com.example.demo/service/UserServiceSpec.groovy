package com.example.demo.service

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import spock.lang.Specification

class UserServiceSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def userService = new UserService(userRepository)

    def "should return matching users by name"() {
        given:
        userRepository.findAll() >> [
                new User(1L, "John", "a@a.com"),
                new User(2L, "Jane", "b@b.com"),
                new User(3L, "Bob", "c@c.com")
        ]

        when:
        def result = userService.findUsersByName("jo")

        then:
        result*.name == ["John"]
    }
}