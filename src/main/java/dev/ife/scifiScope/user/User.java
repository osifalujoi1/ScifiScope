package dev.ife.scifiScope.user;
//Create a template/record based off the type of info provided about the users
public record User(
        Integer id,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {}
