package ru.ivmiit.azat.numericalmethods.methods;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Row<T> {
    private T x;
    private T y;
}
