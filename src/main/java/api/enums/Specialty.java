package api.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Specialty {

    GINEOCOLOGISTY(1,"gineocologisty");

    private final Integer id;
    private final String description;

    Specialty(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Specialty findById(Integer id){
        return Stream.of(Specialty.values())
                .filter(value -> value.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", id)));
    }
    public static Specialty findByDescription(String description){
        return Stream.of(Specialty.values())
                .filter(value -> value.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", description)));
    }
}
