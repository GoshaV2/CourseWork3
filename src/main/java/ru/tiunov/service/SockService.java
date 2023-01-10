package ru.tiunov.service;

import exception.InsufficientQuantityException;
import exception.NotFoundElement;
import ru.tiunov.dto.SockDto;
import ru.tiunov.model.Sock;

public interface SockService {
    void addSocks(SockDto.Request.Standard sockStandard);
    void realiseSocks(SockDto.Request.Standard sockStandard) throws NotFoundElement, InsufficientQuantityException;
    SockDto.Response.Quantity getQuantityOfSocks(SockDto.Request.GetWithOptions sockWithOptions);
    void writeOffSocks(SockDto.Request.Standard sockStandard) throws NotFoundElement, InsufficientQuantityException;
}