package com.example.yourmode.domain.customer.service;

import com.example.yourmode.domain.business.entity.Business;
import com.example.yourmode.domain.business.service.BusinessOwnerService;
import com.example.yourmode.domain.customer.dto.request.CustomerRequestDto;
import com.example.yourmode.domain.customer.dto.response.CustomerIdResponseDto;
import com.example.yourmode.domain.customer.dto.response.CustomerInfoResponseDto;
import com.example.yourmode.domain.customer.dto.response.CustomerListResponseDto;
import com.example.yourmode.domain.customer.entity.Customer;
import com.example.yourmode.domain.customer.mapper.CustomerMapper;
import com.example.yourmode.domain.customer.repository.CustomerRepository;
import com.example.yourmode.domain.customer.status.CustomerErrorStatus;
import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.domain.member.service.MemberService;
import com.example.yourmode.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerOwnerServiceImpl implements CustomerOwnerService{

    private final CustomerRepository customerRepository;

    private final BusinessOwnerService businessOwnerService;
    private final MemberService memberService;

    @Override
    @Transactional
    public CustomerIdResponseDto createCustomer(Member member, CustomerRequestDto request) {
        // 멤버가 사업을 가지고 있는 지 확인
        Business business = businessOwnerService.getBusinessByMember(member);

        // 이미 등록된 고객인지 확인 (같은 전화 번호가 있는 지)
        if(customerRepository.existsByPhone(request.phone())){
            throw new RestApiException(CustomerErrorStatus.EXIST_CUSTOMER);
        }

        // 고객 생성
        Customer customer = CustomerMapper.toCustomer(request);

        // 사업 저장
        customer.setBusiness(business);

        // 같은 전화번호를 가진 멤버가 있으면 고객이랑 연관관게 매핑
        if(memberService.existsByPhone(request.phone())){
            Member memberByPhone = memberService.getMemberByPhone(request.phone());
            customer.setMember(memberByPhone);
        }

        // DB 저장
        Long id = customerRepository.save(customer).getId();

        return new CustomerIdResponseDto(id);
    }

    @Override
    @Transactional
    public CustomerIdResponseDto updateCustomer(Long customerId, CustomerRequestDto request) {
        // 고객 조회
        Customer customer = getCustomer(customerId);

        // 고객 수정, 저장
        customer.updateCustomer(request);

        Long id = customer.getId();

        return new CustomerIdResponseDto(id);
    }

    @Override
    @Transactional
    public CustomerIdResponseDto deleteCustomer(Long customerId) {
        // 고객 조회
        Customer customer = getCustomer(customerId);

        // DB 삭제
        customerRepository.delete(customer);

        Long id = customer.getId();

        return new CustomerIdResponseDto(id);
    }

    @Override
    public CustomerListResponseDto getCustomerList(Member member,Pageable pageable) {
        // 사업 조회
        Business business = businessOwnerService.getBusinessByMember(member);

        // db 조회
        Page<Customer> customers = customerRepository.findByBusiness(business, pageable);

        return CustomerMapper.toCustomerListResponseDto(customers);
    }

    @Override
    public CustomerInfoResponseDto getCustomerDetail(Long customerId) {
        // 고객 조회
        Customer customer = getCustomer(customerId);

        return CustomerMapper.toCustomerIInfoResponseDto(customer);
    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RestApiException(CustomerErrorStatus.EMPTY_CUSTOMER));
    }
}
