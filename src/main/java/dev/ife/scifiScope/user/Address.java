package dev.ife.scifiScope.user;

public record Address(
     String street,
     String suite,
     String city,
     String zipcode,
     Geo geo
) {}
