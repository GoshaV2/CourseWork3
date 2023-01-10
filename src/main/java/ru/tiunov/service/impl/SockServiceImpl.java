package ru.tiunov.service.impl;

import exception.InsufficientQuantityException;
import exception.NotFoundElement;
import org.springframework.stereotype.Service;
import ru.tiunov.dto.SockDto;
import ru.tiunov.model.Sock;
import ru.tiunov.service.SockService;

import java.util.HashMap;
import java.util.Map;

@Service
public class SockServiceImpl implements SockService {
    private final Map<Sock, Integer> quantityOfSock;

    public SockServiceImpl() {
        quantityOfSock = new HashMap<>();
    }

    @Override
    public void addSocks(SockDto.Request.Standard sockStandard) {
        Sock sock = sockStandard.fromDto();
        int addingQuantity = sockStandard.getQuantity();
        int quantity = quantityOfSock.getOrDefault(sock, 0);
        quantityOfSock.put(sock, quantity + addingQuantity);
    }

    @Override
    public void realiseSocks(SockDto.Request.Standard sockStandard) throws NotFoundElement, InsufficientQuantityException {
        Sock sock = sockStandard.fromDto();
        int reducingQuantity = sockStandard.getQuantity();
        reduceQuantity(sock,reducingQuantity);
    }

    @Override
    public SockDto.Response.Quantity getQuantityOfSocks(SockDto.Request.GetWithOptions sockWithOptions) {
        int quantity = 0;
        for (Map.Entry<Sock, Integer> entry : quantityOfSock.entrySet()) {
            Sock sock = entry.getKey();
            if (sock.isHasInStock() && sock.getSize().equals(sockWithOptions.getSize())
                    && sock.getColor().equals(sockWithOptions.getColor())
                    && sockWithOptions.getCottonMin() <= sock.getCottonPart()
                    && sock.getCottonPart() <= sockWithOptions.getCottonMax()
            ) {
                quantity += entry.getValue();
            }
        }
        return new SockDto.Response.Quantity(quantity);
    }

    @Override
    public void writeOffSocks(SockDto.Request.Standard sockStandard) throws NotFoundElement, InsufficientQuantityException {
        Sock sock = sockStandard.fromDto();
        int reducingQuantity = sockStandard.getQuantity();
        reduceQuantity(sock,reducingQuantity);
    }

    private void reduceQuantity(Sock sock,int reducingQuantity) throws NotFoundElement, InsufficientQuantityException {
        if (!quantityOfSock.containsKey(sock)) {
            throw new NotFoundElement("Not found socks with current parameters");
        }
        for (Map.Entry<Sock, Integer> entry : quantityOfSock.entrySet()) {
            if (entry.getKey().equals(sock)) {
                if (!entry.getKey().isHasInStock() || entry.getValue() < reducingQuantity) {
                    throw new InsufficientQuantityException("There are fewer elements than you are trying to reduce",
                            reducingQuantity, entry.getValue());
                }
                int currentQuantity=entry.getValue();
                int editingQuantity=currentQuantity-reducingQuantity;
                if(editingQuantity==0){
                    entry.getKey().setHasInStock(false);
                }
                entry.setValue(editingQuantity);
            }
        }
    }
}
