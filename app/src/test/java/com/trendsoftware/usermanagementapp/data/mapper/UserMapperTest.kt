package com.trendsoftware.usermanagementapp.data.mapper


import com.google.common.truth.Truth.assertThat
import com.trendsoftware.usermanagementapp.data.local.entity.UserEntity
import com.trendsoftware.usermanagementapp.domain.entity.Gender
import com.trendsoftware.usermanagementapp.domain.entity.User
import org.junit.Test


class UserMapperTest {

    @Test
    fun `given UserEntity when mapped to domain then fields match correctly`() {
        //Given
        val entity =
            UserEntity(
                id = 1,
                name = "Omar",
                age = 30,
                jobTitle = "Backend Dev",
                gender = Gender.MALE
            )
        //When
        val domain = entity.toDomain()
        //Then
        assertThat(domain).isEqualTo(
            User(
                id = 1,
                name = "Omar",
                age = 30,
                jobTitle = "Backend Dev",
                gender = Gender.MALE
            )
        )
    }

    @Test
    fun `given User domain when mapped to entity then fields match correctly`() {
        //Given
        val domain =
            User(
                id = 2,
                name = "Nour",
                age = 22,
                jobTitle = "UI Designer",
                gender = Gender.FEMALE
            )
        //When
        val entity = domain.toEntity()

        //Then
        assertThat(entity).isEqualTo(
            UserEntity(
                id = 2,
                name = "Nour",
                age = 22,
                jobTitle = "UI Designer",
                gender = Gender.FEMALE
            )
        )
    }
}
