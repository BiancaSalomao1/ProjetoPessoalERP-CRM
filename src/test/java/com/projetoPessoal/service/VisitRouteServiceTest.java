package com.projetoPessoal.service;

import com.projetoPessoal.dto.RouteStopDTO;
import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.dto.VisitRouteDTO;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.RouteStop;
import com.projetoPessoal.model.User;
import com.projetoPessoal.model.VisitRoute;
import com.projetoPessoal.repository.UserRepository;
import com.projetoPessoal.repository.VisitRouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitRouteServiceTest {

    @Mock
    private VisitRouteRepository visitRouteRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private VisitRouteService service;

    @Test
    public void shouldFindAllRoutes() {
        VisitRoute route = VisitRoute.builder().id(1L).routeName("Route A").creationDate(LocalDate.now()).status("PENDING").stops(new ArrayList<>()).build();
        when(visitRouteRepository.findAll()).thenReturn(List.of(route));

        List<VisitRouteDTO> result = service.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Route A", result.get(0).routeName());
    }

    @Test
    public void shouldCreateRouteWithStops() {
        UserDTO userDto = new UserDTO(1L, "John", null, null, null, null, null, null, null, null, null, null, null);
        RouteStopDTO stopDto = RouteStopDTO.builder().stopOrder(1).status("PENDING").user(userDto).build();
        VisitRouteDTO dto = VisitRouteDTO.builder().routeName("Route A").creationDate(LocalDate.now()).status("PENDING").stops(List.of(stopDto)).build();

        User user = User.builder().id(1L).name("John").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        VisitRoute savedRoute = VisitRoute.builder()
                .id(1L)
                .routeName("Route A")
                .creationDate(LocalDate.now())
                .status("PENDING")
                .stops(new ArrayList<>())
                .build();
        savedRoute.addStop(RouteStop.builder().id(1L).stopOrder(1).status("PENDING").user(user).build());

        when(visitRouteRepository.save(any(VisitRoute.class))).thenReturn(savedRoute);
        when(userMapper.toDTO(user)).thenReturn(userDto);

        VisitRouteDTO result = service.create(dto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(1, result.stops().size());
        assertEquals("John", result.stops().get(0).user().name());
    }
}
