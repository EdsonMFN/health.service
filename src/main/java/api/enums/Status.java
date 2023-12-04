package api.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Status {

    PAYMENT_CONFIRMED(1,"payment confirmed"),
    PAYMENT_REJECTED(2,"payment rejected"),
    PAYMENT_IN_PROCESSING(3,"payment in processing");

    private Integer id;
    private String description;

    Status(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Status findById(Integer id){
        return Stream.of(Status.values())
                .filter(value -> value.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Status %s not found",id)));
    }
    public static Status findBydescription(String description){
        return Stream.of(Status.values())
                .filter(value -> value.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Status %s not found",description)));
    }

}
