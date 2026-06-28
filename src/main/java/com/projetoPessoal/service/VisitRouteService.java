package com.projetoPessoal.service;

import com.projetoPessoal.dto.RouteStopDTO;
import com.projetoPessoal.dto.VisitRouteDTO;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.RouteStop;
import com.projetoPessoal.model.User;
import com.projetoPessoal.model.VisitRoute;
import com.projetoPessoal.repository.UserRepository;
import com.projetoPessoal.repository.VisitRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitRouteService {

    private final VisitRouteRepository visitRouteRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<VisitRouteDTO> findAll() {
        return visitRouteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VisitRouteDTO findById(Long id) {
        return visitRouteRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("VisitRoute not found"));
    }

    @Transactional
    public VisitRouteDTO create(VisitRouteDTO dto) {
        VisitRoute route = VisitRoute.builder()
                .routeName(dto.routeName())
                .creationDate(dto.creationDate() != null ? dto.creationDate() : LocalDate.now())
                .status(dto.status() != null ? dto.status() : "PENDING")
                .stops(new ArrayList<>())
                .build();

        if (dto.stops() != null) {
            for (RouteStopDTO stopDto : dto.stops()) {
                User user = userRepository.findById(stopDto.user().id())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                
                RouteStop stop = RouteStop.builder()
                        .stopOrder(stopDto.stopOrder())
                        .status(stopDto.status() != null ? stopDto.status() : "PENDING")
                        .user(user)
                        .build();
                route.addStop(stop);
            }
        }

        return toDTO(visitRouteRepository.save(route));
    }

    @Transactional
    public VisitRouteDTO update(Long id, VisitRouteDTO dto) {
        VisitRoute route = visitRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VisitRoute not found"));

        route.setRouteName(dto.routeName());
        if (dto.status() != null) {
            route.setStatus(dto.status());
        }

        route.getStops().clear();

        if (dto.stops() != null) {
            for (RouteStopDTO stopDto : dto.stops()) {
                User user = userRepository.findById(stopDto.user().id())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                
                RouteStop stop = RouteStop.builder()
                        .stopOrder(stopDto.stopOrder())
                        .status(stopDto.status() != null ? stopDto.status() : "PENDING")
                        .user(user)
                        .build();
                route.addStop(stop);
            }
        }

        return toDTO(visitRouteRepository.save(route));
    }

    @Transactional
    public void delete(Long id) {
        visitRouteRepository.deleteById(id);
    }

    private VisitRouteDTO toDTO(VisitRoute route) {
        List<RouteStopDTO> stopDTOs = route.getStops().stream()
                .map(s -> RouteStopDTO.builder()
                        .id(s.getId())
                        .stopOrder(s.getStopOrder())
                        .status(s.getStatus())
                        .user(userMapper.toDTO(s.getUser()))
                        .build())
                .collect(Collectors.toList());

        return VisitRouteDTO.builder()
                .id(route.getId())
                .routeName(route.getRouteName())
                .creationDate(route.getCreationDate())
                .status(route.getStatus())
                .stops(stopDTOs)
                .build();
    }
}
