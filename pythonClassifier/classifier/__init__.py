import nltk
nltk.download('punkt')
from nltk.stem.lancaster import LancasterStemmer

stemmer = LancasterStemmer()

training_data = []
training_data.append({"category":"meat", "word":"pork"})
training_data.append({"category":"meat", "word":"mince"})
training_data.append({"category":"meat", "word":"beef"})
training_data.append({"category":"meat", "word":"sausage"})
training_data.append({"category":"meat", "word":"chicken"})
training_data.append({"category":"meat", "word":"lamb"})
training_data.append({"category":"meat", "word":"turkey"})


training_data.append({"category":"freshveg", "word":"turkey"})
training_data.append({"category":"freshveg", "word":"turkey"})
training_data.append({"category":"freshveg", "word":"turkey"})
training_data.append({"category":"freshveg", "word":"turkey"})
training_data.append({"category":"freshveg", "word":"turkey"})
training_data.append({"category":"freshveg", "word":"turkey"})
training_data.append({"category":"freshveg", "word":"turkey"})


print ("%s sentences of training data" % len(training_data))


corpus_words = {}
category_words = {}

categories = list(set([a['category'] for a in training_data]))
for c in categories:
    # prepare words in category
    category_words[c] = []

# loop through each sentence in our training data
for data in training_data:
    # tokenize each sentence into words
    for word in nltk.word_tokenize(data['word']):
        # ignore a some things
        if word not in ["?", "'s"]:
            # lowercase each word
            stemmed_word = word.lower()
            # have we not seen this word already?
            if stemmed_word not in corpus_words:
                corpus_words[stemmed_word] = 1
            else:
                corpus_words[stemmed_word] += 1

            # add the word to our words in class list
            category_words[data['category']].extend([stemmed_word])

# we now have each stemmed word and the number of occurances of the word in our training corpus (the word's commonality)
print ("Corpus words and counts: %s \n" % corpus_words)
# also we have all words in each class
print ("Class words: %s" % category_words)

# calculate a score for a given class
def calculate_class_score(sentence, class_name, show_details=True):
    score = 0
    # tokenize each word in our new sentence
    for word in nltk.word_tokenize(sentence):
        # check to see if the stem of the word is in any of our classes
        if word.lower() in category_words[class_name]:
            # treat each word with same weight
            score += 1

            if show_details:
                print ("   match: %s" % word.lower() )
    return score




# we can now calculate a score for a new sentence
sentence = "mince?"

# now we can find the class with the highest score
for c in category_words.keys():
    print ("Class: %s  Score: %s \n" % (c, calculate_class_score(sentence, c)))


