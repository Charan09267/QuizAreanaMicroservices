package net.contestmicroservice.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContestStatus {

    DRAFT,
    UPCOMING,
    LIVE,
    COMPLETED,
    CANCELLED
}
