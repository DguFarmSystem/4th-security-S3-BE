package farmsystem.backend.domain.member.controller;

import farmsystem.backend.domain.member.dto.request.MemberLoginRequest;
import farmsystem.backend.domain.member.dto.request.MemberSignupRequest;
import farmsystem.backend.domain.member.dto.response.MemberLoginResponse;
import farmsystem.backend.domain.member.dto.response.MemberResponse;
import farmsystem.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Signup, Login", description = "회원가입, 로그인 API")
public interface MemberApi {

    @Operation(
            summary = "회원가입",
            description = "멤버를 생성합니다. \n" +
                    "실시간 프로필 생성."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResponse.class))),
            @ApiResponse(responseCode = "404", description = "이미 이메일이 존재함"),
    })
    ResponseEntity<SuccessResponse<?>> signUp(@RequestBody MemberSignupRequest request);

    @Operation(
            summary = "로그인",
            description = "멤버를 반환합니다. \n" +
                    "email로 토큰을 만들어 바디에 넣었습니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "로그인 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberLoginResponse.class))),
            @ApiResponse(responseCode = "404", description = "이메일 혹은 비밀번호가 잘못되었음"),
    })
    ResponseEntity<SuccessResponse<?>> login(@RequestBody MemberLoginRequest request);


}
