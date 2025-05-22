package farmsystem.backend.domain.profile.controller;

import farmsystem.backend.domain.profile.dto.request.CreateProfileRequest;
import farmsystem.backend.domain.profile.service.ProfileService;
import farmsystem.backend.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController implements ProfileApi {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createProfile(@RequestParam Long memberId,
                                                            @RequestBody CreateProfileRequest request) {
        return SuccessResponse.created(profileService.createProfile(memberId, request));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getProfileList(@RequestParam Long memberId) {
        return SuccessResponse.ok(profileService.getProfileList(memberId));
    }
}
