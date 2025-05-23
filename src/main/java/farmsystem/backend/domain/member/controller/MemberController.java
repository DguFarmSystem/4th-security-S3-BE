package farmsystem.backend.domain.member.controller;

import farmsystem.backend.domain.member.dto.request.MemberLoginRequest;
import farmsystem.backend.domain.member.dto.request.MemberSignupRequest;
import farmsystem.backend.domain.member.service.MemberService;
import farmsystem.backend.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController implements MemberApi{
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> signUp(@Valid @RequestBody MemberSignupRequest request) {
        return SuccessResponse.created(memberService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<?>> login(@Valid @RequestBody MemberLoginRequest request) {
        return SuccessResponse.ok(memberService.login(request));
    }
}
