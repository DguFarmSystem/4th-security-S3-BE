package farmsystem.backend.domain.profile.controller;

import farmsystem.backend.domain.profile.dto.request.CreateProfileRequest;
import farmsystem.backend.domain.profile.dto.response.ProfileResponse;
import farmsystem.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Profile", description = "프로필 API")
public interface ProfileApi {

    @Operation(
            summary = "시점 프로필 생성",
            description = "시점 프로필을 생성합니다.  \n" +
                    "기본 프로필(실시간거래)은 회원가입 시 생성됩니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "프로필 생성 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
    })
    ResponseEntity<SuccessResponse<?>> createProfile(@AuthenticationPrincipal Long memberId,
                                                     @RequestBody CreateProfileRequest request);

    @Operation(
            summary = "프로필 목록 조회",
            description = "회원이 생성한 프로필 목록을 조회합니다.  \n" +
                    "프로필 목록은 기본 프로필(실시간거래)과 시점 프로필로 나뉘어져 있습니다."
    )
    @ApiResponse(responseCode = "200", description = "프로필 목록 조회 성공",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = ProfileResponse.class)))
    )
    ResponseEntity<SuccessResponse<?>> getProfileList(@AuthenticationPrincipal Long memberId);
}
