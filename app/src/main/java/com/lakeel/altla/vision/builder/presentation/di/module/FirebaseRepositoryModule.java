package com.lakeel.altla.vision.builder.presentation.di.module;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import com.lakeel.altla.vision.data.repository.firebase.ConnectionRepository;
import com.lakeel.altla.vision.data.repository.firebase.PublicActorRepository;
import com.lakeel.altla.vision.data.repository.firebase.PublicAreaDescriptionRepository;
import com.lakeel.altla.vision.data.repository.firebase.PublicAreaRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserActorRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserAreaDescriptionFileRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserAreaDescriptionRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserAreaRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserCurrentAreaSettingsRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserDeviceConnectionRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserDeviceRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserImageAssetFileRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserImageAssetRepository;
import com.lakeel.altla.vision.data.repository.firebase.UserProfileRepository;
import com.lakeel.altla.vision.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public final class FirebaseRepositoryModule {

    @ActivityScope
    @Provides
    ConnectionRepository provideConnectionRepository(FirebaseDatabase database) {
        return new ConnectionRepository(database);
    }

    @ActivityScope
    @Provides
    UserDeviceConnectionRepository provideUserDeviceConnectionRepository(FirebaseDatabase database) {
        return new UserDeviceConnectionRepository(database);
    }

    @ActivityScope
    @Provides
    UserProfileRepository provideUserProfileRepository(FirebaseDatabase database) {
        return new UserProfileRepository(database);
    }

    @ActivityScope
    @Provides
    UserDeviceRepository provideUserDeviceRepository(FirebaseDatabase database) {
        return new UserDeviceRepository(database);
    }

    @ActivityScope
    @Provides
    PublicAreaRepository providePublicAreaRepository(FirebaseDatabase database) {
        return new PublicAreaRepository(database);
    }

    @ActivityScope
    @Provides
    UserAreaRepository provideUserAreaRepository(FirebaseDatabase database) {
        return new UserAreaRepository(database);
    }

    @ActivityScope
    @Provides
    PublicAreaDescriptionRepository providePublicAreaDescriptionRepository(FirebaseDatabase database) {
        return new PublicAreaDescriptionRepository(database);
    }

    @ActivityScope
    @Provides
    UserAreaDescriptionRepository provideUserAreaDescriptionRepository(FirebaseDatabase database) {
        return new UserAreaDescriptionRepository(database);
    }

    @ActivityScope
    @Provides
    UserAreaDescriptionFileRepository provideUserAreaDescriptionFileRepository(FirebaseStorage storage) {
        return new UserAreaDescriptionFileRepository(storage);
    }

    @ActivityScope
    @Provides
    UserCurrentAreaSettingsRepository provideUserCurrentProjectRepository(FirebaseDatabase database) {
        return new UserCurrentAreaSettingsRepository(database);
    }

    @ActivityScope
    @Provides
    UserImageAssetRepository provideUserImageAssetRepository(FirebaseDatabase database) {
        return new UserImageAssetRepository(database);
    }

    @ActivityScope
    @Provides
    UserImageAssetFileRepository provideUserImageAssetFileRepository(FirebaseStorage storage) {
        return new UserImageAssetFileRepository(storage);
    }

    @ActivityScope
    @Provides
    PublicActorRepository providePublicActorRepository(FirebaseDatabase database) {
        return new PublicActorRepository(database);
    }

    @ActivityScope
    @Provides
    UserActorRepository provideUserActorRepository(FirebaseDatabase database) {
        return new UserActorRepository(database);
    }
}
