package com.yuva.cryptopulse.Entity;

import com.yuva.cryptopulse.domain.VERIFICATION_TYPE;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VERIFICATION_TYPE sentTo;

}
