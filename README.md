# Upload Library to Maven Central

## System & Tools
- Max os Sierra version 10.12.6
- IntelliJ IDEA 2017.3
- Apache Maven 3.5.0

### Step 1 : Register Sonatype Account

- new issue in [sonatype](https://issues.sonatype.org/)
  - click button `Create`
  - Project : select `Community Support - Open Source Project Repository Hosting`
  - Issue Type : select `New Project`
  - Summary : input Library/Project
  - Group Id : The groupId of your artifacts, e.g. org.sonatype.oss. Need help choosing a groupId? [Read](http://central.sonatype.org/pages/choosing-your-coordinates.html)
  - Project URL : Location of the Project Website, e.g. `https://github.com/nopparat-mkw/DateUtility`
  - SCM url : Location of source control system, e.g. `https://github.com/nopparat-mkw/DateUtility.git`
- waiting team Sonanype approved
  - example 
  ``` 
  com.github.nopparat-mkw has been prepared, now user(s) nopparat-mkw can:
    - Deploy snapshot artifacts into repository https://oss.sonatype.org/content/repositories/snapshots
    - Deploy release artifacts into the staging repository https://oss.sonatype.org/service/local/staging/deploy/maven2
    - Promote staged artifacts into repository 'Releases'
    - Download snapshot and release artifacts from group https://oss.sonatype.org/content/groups/public
    - Download snapshot, release and staged artifacts from staging group https://oss.sonatype.org/content/groups/staging
  please comment on this ticket when you promoted your first release, thanks
  ```
  
### Step 2 : Create Maven Project and Library
### Step 3 : Signing Artifacts with GPG

- Installing GnuPG
  - Download GPG from [http://www.gnupg.org/download/](http://www.gnupg.org/download/) or install it with your favorite package manager and verify it by running a gpg command with the version flag.
  ```
  $ gpg --version
  gpg (GnuPG) 2.2.2
  libgcrypt 1.8.1
  Copyright (C) 2017 Free Software Foundation, Inc.
  License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>
  This is free software: you are free to change and redistribute it.
  There is NO WARRANTY, to the extent permitted by law.
  
  Home: /Users/9pi/.gnupg
  Supported algorithms:
  Pubkey: RSA, ELG, DSA, ECDH, ECDSA, EDDSA
  Cipher: IDEA, 3DES, CAST5, BLOWFISH, AES, AES192, AES256, TWOFISH, CAMELLIA128, CAMELLIA192, CAMELLIA256
  Hash: SHA1, RIPEMD160, SHA256, SHA384, SHA512, SHA224
  Compression: Uncompressed, ZIP, ZLIB, BZIP2
  ``` 
  ```
  $ gpg2 --version
  gpg (GnuPG) 2.2.2
  libgcrypt 1.8.1
  Copyright (C) 2017 Free Software Foundation, Inc.
  License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>
  This is free software: you are free to change and redistribute it.
  There is NO WARRANTY, to the extent permitted by law.
  
  Home: /Users/9pi/.gnupg
  Supported algorithms:
  Pubkey: RSA, ELG, DSA, ECDH, ECDSA, EDDSA
  Cipher: IDEA, 3DES, CAST5, BLOWFISH, AES, AES192, AES256, TWOFISH, CAMELLIA128, CAMELLIA192, CAMELLIA256
  Hash: SHA1, RIPEMD160, SHA256, SHA384, SHA512, SHA224
  Compression: Uncompressed, ZIP, ZLIB, BZIP2
  ``` 
  - Generating a Key Pair
  ```
  $ gpg --gen-key
  ```
  - Listing Keys and copy `6FC50F1CF4E8F129` use Step 4
  ```
  $ gpg --list-secret-keys --keyid-format LONG
  sec   rsa2048/6FC50F1CF4E8F129 2017-11-13 [SC] [expires: 2019-11-13]
        325672C10B9E302DA1CB696C6FC50F1CF4E8F129
  uid                 [ultimate] nopparat-mkw <nopparat.mueangkaew@gmail.com>
  ssb   rsa2048/B4BCCFD8A59F479E 2017-11-13 [E] [expires: 2019-11-13]
  ```
  - Export Public Key/Private Key in format
  ```
  gpg -a --export yourmail@email.com > public_key_sender.asc
  gpg -a --export-secret-key yourmail@email.com > private_key_sender.asc
  ```
  - cat `public_key_sender.asc` and copy key
  - open url [https://pgp.mit.edu/](https://pgp.mit.edu/)
  - input key in `Submit a key` and Click `Submit this key to keyserver!`  if finish show text below
  ```
  Key block added to key server database. New public keys added: 
  3 key(s) added successfully.
  ```
  
### Step 4 : Deploying to OSSRH with Apache Maven

- First Deployment
  - The above configurations will get the user account details to deploy to OSSRH from your Maven `settings.xml` file. A minimal settings with the authentication is (path ~/.m2)
  ```
  <settings>
    <servers>
      <server>
        <id>ossrh</id>
        <username>your-jira-id</username>
        <password>your-jira-pwd</password>
      </server>
    </servers>
    <profiles>
      <profile>
        <id>ossrh</id>
        <activation>
          <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
          <gpg.executable>gpg2</gpg.executable>
          <gpg.passphrase>6FC50F1CF4E8F129</gpg.passphrase>
        </properties>
      </profile>
    </profiles>
  </settings>
  ```
  - in pom.xml inside project [see](https://github.com/nopparat-mkw/DateUtility/blob/master/pom.xml)