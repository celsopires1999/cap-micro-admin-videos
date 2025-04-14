#!/bin/bash

echo 'export SDKMAN_DIR="$HOME/.sdkman"' >> /home/dev/.bashrc
echo '[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"' >> /home/dev/.bashrc

echo 'export SDKMAN_DIR="$HOME/.sdkman"' >> /home/dev/.zshrc
echo '[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"' >> /home/dev/.zshrc

tail -f /dev/null