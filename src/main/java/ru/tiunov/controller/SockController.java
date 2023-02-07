package ru.tiunov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tiunov.dto.SockDto;
import ru.tiunov.exception.InsufficientQuantityException;
import ru.tiunov.exception.NotFoundElement;
import ru.tiunov.model.Color;
import ru.tiunov.model.Size;
import ru.tiunov.service.SockService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/socks")
@Validated
@Tag(name = "Работа с товаром 'Носки'")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @Operation(
            summary = "Добавить носки на склад"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Успешно добавлен товар"),
                    @ApiResponse(responseCode = "400", description = "Неверно заполнены данные")
            }
    )
    @PostMapping
    public ResponseEntity addSocks(@Valid @RequestBody SockDto.Request.Standard sock) {
        sockService.addSocks(sock);
        return ResponseEntity.ok(sock);
    }

    @Parameters(value = {
            @Parameter(
                    name = "size",
                    schema = @Schema(implementation = Size.class)
            )
    })
    @Operation(
            summary = "Получить количество носков на складе"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Полученно количество товара"),
                    @ApiResponse(responseCode = "400", description = "Неверно заполнены данные")
            }
    )
    @GetMapping
    public ResponseEntity getSocks(@RequestParam("color") @NotNull Color color, @RequestParam @NotNull Size size,
                                   @RequestParam(required = false, defaultValue = "0") @Min(0) @Max(100) int cottonMin,
                                   @RequestParam(required = false, defaultValue = "100") @Min(0) @Max(100) int cottonMax) {
        SockDto.Request.GetWithOptions sock = new SockDto.Request.GetWithOptions(color, size, cottonMax, cottonMin);
        return ResponseEntity.ok(sockService.getQuantityOfSocks(sock));
    }

    @Operation(
            summary = "Забрать носки со склада"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Успешно получен товар"),
                    @ApiResponse(responseCode = "404", description = "Не был найден товар с такими характеристиками"),
                    @ApiResponse(responseCode = "204", description = "Была попытка списать товаров больше, чем имеется"),
                    @ApiResponse(responseCode = "400", description = "Неверно заполнены данные")
            }
    )
    @PutMapping
    public ResponseEntity realiseSocks(@Valid @RequestBody SockDto.Request.Standard sock) {
        try {
            sockService.realiseSocks(sock);
        } catch (NotFoundElement e) {
            return ResponseEntity.notFound().build();
        } catch (InsufficientQuantityException e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Списать бракованный товар"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Успешно списан товар"),
                    @ApiResponse(responseCode = "404", description = "Не был найден товар с такими характеристиками"),
                    @ApiResponse(responseCode = "204", description = "Была попытка списать товаров больше, чем имеется"),
                    @ApiResponse(responseCode = "400", description = "Неверно заполнены данные")
            }
    )
    @DeleteMapping
    public ResponseEntity writeOffSocks(@Valid @RequestBody SockDto.Request.Standard sock) {
        sockService.writeOffSocks(sock);
        return ResponseEntity.ok().build();
    }
}
