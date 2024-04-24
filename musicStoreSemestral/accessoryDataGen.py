import pandas as pd
from faker import Faker
import random

faker = Faker()

def generate_accessory_data(num_entries):
    data = {
        'ISBN': [faker.isbn13(separator="") for _ in range(num_entries)],
        'type': [random.choice(
                    ['Bass Case',
                    'XLR Cable', 
                    'Guitar Case', 
                    'Guitar Strings',
                    'Bass Pedal' 
                    'Jack Cable', 
                    'Pick',
                    'Power Cable',
                    'Earplugs',
                    'Effects Pedal'
                    'Expression Pedal',
                    'Snare Head',
                    'Tom Head',
                    'Kick Head',
                    'Cymbal Stand',
                    'Mute']
                ) for _ in range(num_entries)]
    }
    return pd.DataFrame(data)

def main():
    num_entries = 32000  # Number of entries to generate
    df = generate_accessory_data(num_entries)
    df.to_csv('accessory_data.csv', index=False, header=False)  # Save to CSV without header

if __name__ == "__main__":
    main()
