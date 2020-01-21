package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import com.example.project.domain.entities.Client;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.ClientRepository;

import org.aspectj.lang.annotation.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * ClientServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    private ClientRepository repositoryMock;

    @InjectMocks
    private ClientService service;

    private final Integer id = 1;
    private final String name = "Some string";
    private final String phone = "987654321";

    Client entity = Client.builder().id(id).name(name).phone(phone).build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {

        // given no data

        // then
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("Client Not found");

        // when
        service.findById(1);
    }

    @Test
    public void should_createClient() {
        // given
        when(repositoryMock.save(entity)).thenReturn(entity);

        //when
        Client model = service.createClient(entity);

        //then
        assertNotNull("Client deve ser retornado!", model);
    }

    @Test
    public void should_putClient() {
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));
        when(repositoryMock.save(entity)).thenReturn((entity));

        Client model = Client.builder().name("Test").phone("987654321").build();
        Client client = service.putClient(id, model);

        assertEquals(client, Client.builder().id(1).name("Test").phone("987654321").build());
    }

    @Test
    public void should_deleteClient() {
        should_findById();
        service.deleteClient(1);
    }

    @Test
    public void should_findById() {

        // given
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

        // when
        Client model = service.findById(anyInt());

        // then
        verify(repositoryMock, times(1)).findById(anyInt());
        assertNotNull("Client deve ser encontrado!", model);
    }

    @Test
    public void should_ListOneItem() {
        List<Client> list = new ArrayList<>();
        list.add(entity);
        when(repositoryMock.findAll()).thenReturn(list);

        List<Client> listR = service.listClient();

        verify(repositoryMock, times(1)).findAll();
        assertNotNull("Array não deve ser nulo", listR);
        assertEquals("Array deve ser de tamanho 1", 1, listR.size());
    }

    @Test
    public void should_listByPhone() {
        List<Client> = 
    }

} 