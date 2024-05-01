class Trie:
    def __init__(self):
        self.cnt = 0
        self.children = {}

    def insert(self, word):
        curr = self
        for c in word:
            curr.cnt += 1
            if c not in curr.children:
                curr.children[c] = Trie()
            curr = curr.children[c]
        curr.cnt += 1

    def search(self, q):
        curr = self
        for c in q:
            if c == "?":
                return curr.cnt
            if c not in curr.children:
                return 0
            curr = curr.children[c]
        return curr.cnt
