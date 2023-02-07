package ru.tiunov.dto;

import lombok.Value;
import ru.tiunov.model.Color;
import ru.tiunov.model.Size;
import ru.tiunov.model.Sock;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SockDto {
    private interface ColorInterface {
        @NotNull Color getColor();
    }

    private interface SizeInterface {
        @NotNull
        Size getSize();
    }

    private interface CottonPart {
        @Max(100)
        @Min(0)
        @Positive
        int getCottonPart();
    }

    private interface Quantity {
        @Min(1)
        int getQuantity();
    }

    private interface CottonMax {
        @Max(100)
        @Min(0)
        @Positive
        int getCottonMax();
    }

    private interface CottonMin {
        @Max(100)
        @Min(0)
        @Positive
        int getCottonMin();
    }


    public static class Request {
        @Value
        public static class Standard implements ColorInterface, SizeInterface, CottonPart, Quantity {
            Color color;
            Size size;
            int cottonPart;
            int quantity;

            public Sock fromDto() {
                return Sock.builder()
                        .size(this.getSize())
                        .color(this.getColor())
                        .cottonPart(this.getCottonPart())
                        .hasInStock(true)
                        .build();
            }
        }

        @Value
        public static class GetWithOptions implements ColorInterface, SizeInterface, CottonMax, CottonMin {
            Color color;
            Size size;
            int cottonMax;
            int cottonMin;
        }
    }

    public static class Response {
        @Value
        public static class Quantity implements SockDto.Quantity {
            int quantity;
        }
    }
}
