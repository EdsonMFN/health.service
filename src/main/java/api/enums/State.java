package api.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum State {

    AM(1,"Amazonas");

    private final Integer id;
    private final String description;

    State(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static State findById(Integer id){
        return Stream.of(State.values())
                .filter(value -> value.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", id)));
    }
    public static State findByDescription(String description){
        return Stream.of(State.values())
                .filter(value -> value.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", description)));
    }
}
